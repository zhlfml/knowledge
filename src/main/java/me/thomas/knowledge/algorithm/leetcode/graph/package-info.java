/**
 * 图算法：
 * 1. Dijkstra最短路径
 * <p>
 * 2. 环检测和拓扑排序
 * <p>
 * 3. 二分图判定
 * <p>
 * 4. 名流
 * <p>
 * 5. Kurskal最小生成树
 * <p>
 * 6. Prim最小生成树
 * <p>
 * 环检测和拓扑排序： 有向图
 * <p>
 * 最小生成树： 无向图
 * <p>
 * <p>
 * 注意事项：
 * 1. 并查集算法的union方法计算连通分量时一定要关注之前是否已连通，如果已连通则不能再次连通，否则影响连通分量的计算。
 * <pre>
 * if (rootX == rootY) {
 *     return;
 * }
 * </pre>
 */
package me.thomas.knowledge.algorithm.leetcode.graph;