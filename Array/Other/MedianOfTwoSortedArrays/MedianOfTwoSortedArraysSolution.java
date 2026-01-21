package Array.Other.MedianOfTwoSortedArrays;

public class MedianOfTwoSortedArraysSolution {

    // nums1 = {5,7,11,15,20} m=5
    // nums2 = {2,3,5,6,10,18,19} n=7
    // combined = {2,3,5,5,6,7,10,11,15,18,19,20} m+n=12
    // part1 = 2, part2 = 4
    // nums1Left = {5,11}, nums1Right = {11,15,20}
    // nums2Left = {2,3,5,6}, nums2Right = {10,10,18,19}

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length){
            return findMedianSortedArrays(nums2, nums1);
        }
        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m;

        while(left <= right){
            int part1 = (left + right)/2;
            int part2 = ((m + n + 1)/2) - part1;

            int maxLeft1 = part1 == 0 ? Integer.MIN_VALUE : nums1[part1-1];
            int minRight1 = part1 == m ? Integer.MAX_VALUE : nums1[part1];
            int maxLeft2 = part2 == 0 ? Integer.MIN_VALUE : nums2[part2-1];
            int minRight2 = part2 == n ? Integer.MAX_VALUE : nums2[part2];

            if(maxLeft1 <= minRight2 && maxLeft2 <= minRight1){
                if((m+n)%2 == 0){
                    return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
                } else{
                    return Math.max(maxLeft1,maxLeft2);
                }
            } else if (maxLeft1 > minRight2) {
                right = part1 - 1;
            } else {
                left = part1 + 1;
            }
        }
        return 0.0;
    }

    public static void main(String[] args) {
        int[] nums1 = {5,11,12,15,20};
        int[] nums2 = {2,3,5,6,10,18,19};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

}
