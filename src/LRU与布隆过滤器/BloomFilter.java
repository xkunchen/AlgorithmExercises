package LRU与布隆过滤器;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.*;
/**
 * @Author: xkunchen
 * @Description:布隆过滤器模板
 * @Data: 2021/4/9
 **/

/**
 * java的做法：
 * 首先了解布隆过滤器的原理：没有肯定返回false，并且表示肯定这个数没有，有返回true，表示该数据可能存在
 * 下面解法采用BitSet 和 Random来做
 * BitSet 模拟hash表，Random用来生成随机数，并且同个hashcode的对象生成的Random次数相同，在bitSet的位置相同
 * 不太了解为什么这么做
 */
public class BloomFilter implements Cloneable {
    private BitSet hashes;
    private RandomInRange prng;
    private int k; // Number of hash functions
    private static final double LN2 = 0.6931471805599453; // ln(2)

    /**
     * Create a new bloom filter.
     * @param n Expected number of elements
     * @param m Desired size of the container in bits
     **/
    public BloomFilter(int n, int m) {
        k = (int) Math.round(LN2 * m / n);
        if (k <= 0) k = 1;
        this.hashes = new BitSet(m);
        this.prng = new RandomInRange(m, k);
    }

    /**
     * Create a bloom filter of 1Mib.
     * @param n Expected number of elements
     **/
    public BloomFilter(int n) {
        this(n, 1024*1024*8);
    }

    /**
     * Add an element to the container
     **/
    public void add(Object o) {
        prng.init(o);
        for (RandomInRange r : prng)
            hashes.set(r.value);
    }
    /**
     * If the element is in the container, returns true.
     * If the element is not in the container, returns true with a probability ≈ e^(-ln(2)² * m/n), otherwise false.
     * So, when m is large enough, the return value can be interpreted as:
     *    - true  : the element is probably in the container
     *    - false : the element is definitely not in the container
     **/
    public boolean contains(Object o) {
        //设置这个会使同个hash值随机生存的数据一样
        prng.init(o);
        for (RandomInRange r : prng)
            if (!hashes.get(r.value))
                return false;
        return true;
    }

    /**
     * Removes all of the elements from this filter.
     **/
    public void clear() {
        hashes.clear();
    }

    /**
     * Create a copy of the current filter
     **/
    public BloomFilter clone() throws CloneNotSupportedException {
        return (BloomFilter) super.clone();
    }

    /**
     * Generate a unique hash representing the filter
     **/
    public int hashCode() {
        return hashes.hashCode() ^ k;
    }

    /**
     * Test if the filters have equal bitsets.
     * WARNING: two filters may contain the same elements, but not be equal
     * (if the filters have different size for example).
     */
    public boolean equals(BloomFilter other) {
        return this.hashes.equals(other.hashes) && this.k == other.k;
    }

    /**
     * Merge another bloom filter into the current one.
     * After this operation, the current bloom filter contains all elements in
     * other.
     **/
    public void merge(BloomFilter other) {
        if (other.k != this.k || other.hashes.size() != this.hashes.size()) {
            throw new IllegalArgumentException("Incompatible bloom filters");
        }
        this.hashes.or(other.hashes);
    }

    private class RandomInRange
            implements Iterable<RandomInRange>, Iterator<RandomInRange> {

        private Random prng;
        private int max; // Maximum value returned + 1
        private int count; // Number of random elements to generate
        private int i = 0; // Number of elements generated
        public int value; // The current value

        RandomInRange(int maximum, int k) {
            max = maximum;
            count = k;
            prng = new Random();
        }
        public void init(Object o) {
            prng.setSeed(o.hashCode());
        }
        public Iterator<RandomInRange> iterator() {
            i = 0;
            return this;
        }
        public RandomInRange next() {
            i++;
            value = prng.nextInt() % max;
            if (value<0) value = -value;
            return this;
        }
        public boolean hasNext() {
            return i < count;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        BloomFilter b=new BloomFilter(1000);
        b.add("abc");
        b.add("c");
        b.add("d");
        b.add("ee");
        b.add("sss");
        b.add("ss");
        System.out.println(b.contains("ddd"));
        System.out.println(b.contains("ss"));
        /*Random r = new Random();
        r.setSeed(555L);
        for (int j = 0; j < 5; j++) {
            System.out.println(" " + r.nextInt(100) + ", ");
        }*/
    }
}
class Test {
    int elements =  1_000_000;
    int bitsize  = 10_000_000;
    BloomFilter filter;
    Random prng;
    ThreadMXBean bean;

    public Test() {
        System.out.format(
                "Testing a bloom filter containing n=%d elements" +
                        " in a bit array of m=%d bits (=%.1fMib) \n\n",
                elements, bitsize, ((float)bitsize/(1024*1024*8))
        );
        bean = ManagementFactory.getThreadMXBean();
        prng = new Random();
        prng.setSeed(0);
        filter = new BloomFilter(elements, bitsize);
    }

    public void testCorrectness() {
        System.out.println("Testing correctness.\n"+
                "Creating a Set and filling it together with our filter...");
        filter.clear();
        Set<Integer> inside = new HashSet<>((int)(elements / 0.75));
        while(inside.size() < elements) {
            int v = prng.nextInt();
            inside.add(v);
            filter.add(v);
            assert filter.contains(v) : "There should be no false negative";
        }

        // testing
        int found = 0, total = 0;
        double rate = 0;
        while (total < elements) {
            int v = prng.nextInt();
            if (inside.contains(v)) continue;
            total++;
            found += filter.contains(v) ? 1 : 0;

            rate = (float) found / total;
            if (total % 1000 == 0 || total == elements) {
                System.out.format(
                        "\rElements incorrectly found to be inside: %8d/%-8d (%3.2f%%)",
                        found, total, 100*rate
                );
            }
        }
        System.out.println("\n");

        double ln2 = Math.log(2);
        double expectedRate = Math.exp(-ln2*ln2 * bitsize / elements);
        assert rate <= expectedRate * 1.10 : "error rate p = e^(-ln2^2*m/n)";
    }

    public void testInsertion() {
        System.out.println("Testing insertion speed...");

        filter.clear();
        long start = bean.getCurrentThreadCpuTime();
        for(int i=0; i<elements; i++) filter.add(prng.nextInt());
        long end = bean.getCurrentThreadCpuTime();
        long time = end - start;

        System.out.format(
                "Inserted %d elements in %d ns.\n" +
                        "Insertion speed: %g elements/second\n\n",
                elements,
                time,
                elements/(time*1e-9)
        );
    }

    public void testQuery() {
        System.out.println("Testing query speed...");

        filter.clear();
        for(int i=0; i<elements; i++) filter.add(prng.nextInt());

        boolean xor = true; // Make sure our result isn’t optimized out
        long start = bean.getCurrentThreadCpuTime();
        for(int i=0; i<elements; i++) xor ^= filter.contains(prng.nextInt());
        long end = bean.getCurrentThreadCpuTime();
        long time = end - start;

        System.out.format(
                "Queried %d elements in %d ns.\n" +
                        "Query speed: %g elements/second\n\n",
                elements,
                time,
                elements/(time*1e-9)
        );
    }

    public void testMerge() throws CloneNotSupportedException {
        System.out.print("Testing merge... ");

        filter.clear();
        BloomFilter filter2 = filter.clone();
        for(int i=0; i<elements; i++) {
            int a, b;
            filter.add(a = prng.nextInt());
            filter2.add(b = prng.nextInt());
            BloomFilter concat = filter.clone();
            concat.merge(filter2);
            assert concat.contains(a) && concat.contains(b) :
                    "merged filters don't lose elements";
        }
        BloomFilter concat1 = filter.clone();
        concat1.merge(filter2);
        BloomFilter concat2 = filter2.clone();
        concat2.merge(filter);
        assert concat1.equals(concat2) : "a.merge(b) = b.merge(a)";
        System.out.println("Done.\n");
    }

    public static void main(String[] args) {
        Test test = new Test();
        if (args.length >= 1) test.elements = Integer.parseInt(args[0]);
        if (args.length >= 2) test.bitsize = Integer.parseInt(args[1]);
        test.testCorrectness();
        test.testInsertion();
        test.testQuery();
    }
}