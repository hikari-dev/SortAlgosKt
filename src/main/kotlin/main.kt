@file:JvmName("Main")

import kotlin.random.Random

fun main() {
    val input = intArrayOf(8, 1, 2, 6, 4, 5, 11, 6, 7)
    val ans = countingSort(input)
    ans.forEach { print("$it ") }
}

/**
 * 交换数组里位于i和j索引的两个数
 */
fun swap(nums: IntArray, i: Int, j: Int) {
    val temp = nums[i]
    nums[i] = nums[j]
    nums[j] = temp
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
 * 选择排序
 * 排序思想：每一趟遍历，将一个最小的数移到序列起始位置。
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 */
fun selectionSort(nums: IntArray) {
    if (nums.size < 2) return
    //i表示第几趟排序
    for (i in 0 until nums.lastIndex) {
        var minIndex = i//minIndex表示每趟排序最小值的索引
        //j表示这趟排序遍历的索引位置
        for (j in i + 1..nums.lastIndex) {
            if (nums[j] < nums[minIndex]) {
                minIndex = j
            }
        }
        swap(nums, i, minIndex)
    }
}

/**
 * 插入排序
 * 排序思想：每一趟遍历，将当前遍历的数插入到已经排好序的序列中的对应位置
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 */
fun insertionSort(nums: IntArray) {
    if (nums.size < 2) return
    //i表示第几趟排序
    for (i in 1..nums.lastIndex) {
        //j表示当前元素每次和有序队列的元素进行比较后的索引
        for (j in i downTo 1) {
            if (nums[j] < nums[j - 1]) {
                swap(nums, j, j - 1)
            } else {
                break
            }
        }
    }
}

/**
 * 归并排序
 * 排序思想：分治
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(n)
 */
fun mergeSort(nums: IntArray): IntArray {
    if (nums.size < 2) return nums
    val mid = nums.size / 2
    val leftArr = nums.copyOfRange(0, mid)
    val rightArr = nums.copyOfRange(mid, nums.lastIndex + 1)
    return merge(mergeSort(leftArr), mergeSort(rightArr))
}

fun merge(nums1: IntArray, nums2: IntArray): IntArray {
    val ans = IntArray(nums1.size + nums2.size)
    //《算法导论》里使用的是哨兵法，给每个数组的最后加上大小为正无穷的哨兵元素，可以省去每次检查是否有数组遍历到头了。
    // 此处使用的双指针法，也具有相同的遍历效果
    var i = 0
    var j = 0
    while (i <= nums1.lastIndex && j <= nums2.lastIndex) {
        if (nums1[i] <= nums2[j]) {
            ans[i + j] = nums1[i]
            i++
        } else {
            ans[i + j] = nums2[j]
            j++
        }
    }
    while (i <= nums1.lastIndex) {
        ans[i + j] = nums1[i]
        i++
    }
    while (j <= nums2.lastIndex) {
        ans[i + j] = nums2[j]
        j++
    }
    return ans
}

/**
 * 快速排序
 */
fun quickSort(nums: IntArray) {
    if (nums.size < 2) return
    quickSortImpl(nums, 0, nums.lastIndex)
}

fun quickSortImpl(nums: IntArray, l: Int, r: Int) {
    if (l < r) {
        val partition = randPartition(nums, l, r)
        quickSortImpl(nums, l, partition)
        quickSortImpl(nums, partition + 1, r)
    }
}

fun partition(nums: IntArray, l: Int, r: Int): Int {
    val pivot = nums[l]
    var i = l
    for (j in l..r) {
        if (pivot > nums[j]) {
            i++
            swap(nums, i, j)
        }
    }
    swap(nums, l, i)
    return i
}

fun randPartition(nums: IntArray, l: Int, r: Int): Int {
    val randIndex = Random.nextInt(l, r + 1)
    swap(nums, randIndex, l)
    return partition(nums, l, r)
}

/**
 * 计数排序
 */
fun countingSort(nums: IntArray): IntArray {
    if (nums.size < 2) return nums
    //获得数组最大值
    var max = Int.MIN_VALUE
    for (num in nums) {
        max = Math.max(num, max)
    }
    //分别统计(0..max)在数组中出现的次数
    val arr = IntArray(max + 1)
    for (num in nums) {
        arr[num] += 1
    }
    //通过累加，计算对于每一个元素，数组中有多少个元素是小于或者等于该元素的
    for (i in 1..arr.lastIndex) {
        arr[i] += arr[i - 1]
    }
    val ans = IntArray(nums.size)
    //倒序遍历数组，根据上面得到的arr，把每个元素放到相应的位置
    for (i in nums.lastIndex downTo 0) {
        ans[arr[nums[i]] - 1] = nums[i]
        arr[nums[i]] -= 1
    }
    return ans
}