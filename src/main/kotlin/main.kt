@file:JvmName("Main")

fun main() {
    val input = intArrayOf(3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48)
    bubbleSort(input)
    println(input.toList())
}

/**
 * 冒泡排序
 * 排序思想：每一趟遍历，将一个最大的数移到序列末尾。
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 */
fun bubbleSort(nums: IntArray) {
    if (nums.size < 2) return
    //i表示第几趟排序
    for (i in 0 until nums.lastIndex - 1) {
        //j表示这趟排序遍历的索引位置
        for (j in 0 until nums.lastIndex - i) {
            if (nums[j] > nums[j + 1]) {
                swap(nums, j, j + 1)
            }
        }
    }
}

/**
 * 交换数组里位于i和j索引的两个数
 */
fun swap(nums: IntArray, i: Int, j: Int) {
    val temp = nums[i]
    nums[i] = nums[j]
    nums[j] = temp
}