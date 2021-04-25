LinkedHashMap 的写法
class LRUCache {
    /**
     * 缓存映射表
     */
    private Map<Integer, DLinkNode> cache = new HashMap<>();
    /**
     * 缓存大小
     */
    private int size;
    /**
     * 缓存容量
     */
    private int capacity;
    /**
     * 链表头部和尾部
     */
    private DLinkNode head, tail;

    public LRUCache(int capacity) {        /**初始化缓存大小，容量和头尾节点 */this.size = 0;
        this.capacity = capacity;
        head = new DLinkNode();
        tail = new DLinkNode();
        head.next = tail;
        tail.prev = head;
    }

    /**     * 获取节点     * @param key 节点的键     * @return 返回节点的值     */
    public int get(int key) {
        DLinkNode node = cache.get(key);
        if (node == null) {
            return -1;
        }       
        /**移动到链表头部  (node);*/
        moveToHead(node);
        return node.value;
    }

    /**     * 添加节点     * @param key 节点的键     * @param value 节点的值     */
    public void put(int key, int value) {
        DLinkNode node = cache.get(key);
        if (node == null) {
            DLinkNode newNode = new DLinkNode(key, value);
            cache.put(key, newNode);            /**添加到链表头部 */addToHead(newNode);
            ++size;            /**如果缓存已满，需要清理尾部节点  */if (size > capacity) {
                DLinkNode tail = removeTail();
                cache.remove(tail.key);
                --size;
            }
        } else {
            node.value = value;            /**移动到链表头部*/moveToHead(node);
        }
    }

    /**     * 删除尾结点     *     * @return 返回删除的节点     */
    private DLinkNode removeTail() {
        DLinkNode node = tail.prev;
        removeNode(node);
        return node;
    }

    /**     * 删除节点     * @param node 需要删除的节点     */
    private void removeNode(DLinkNode node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    /**     * 把节点添加到链表头部     *     * @param node 要添加的节点     */
    private void addToHead(DLinkNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    /**     * 把节点移动到头部     * @param node 需要移动的节点     */
    private void moveToHead(DLinkNode node) {
        removeNode(node);
        addToHead(node);
    }

    /**     * 链表节点类     */
    private static class DLinkNode {
        Integer key;
        Integer value;
        DLinkNode prev;
        DLinkNode next;

        DLinkNode() {
        }

        DLinkNode(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }
}