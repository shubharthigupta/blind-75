package Array.Other.TrappingRainWater;

public class TrappingRainWaterSolution {

    public static int trap(int[] height) {
        //{6,4,2,0,3,2,0,3,1,4,5,3,2,7,5,3,0,1,2,1,3,4,6,8,1,3}
        int n = height.length;
        int[] lmax = new int[n];
        lmax[0] = height[0];
        for (int i = 1; i < n; i++)
            lmax[i] = Math.max(lmax[i - 1], height[i]);

        int[] rmax = new int[n];
        rmax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--)
            rmax[i] = Math.max(rmax[i + 1], height[i]);

        int ans = 0;
        for (int i = 0; i < n; i++)
            ans += Math.min(lmax[i], rmax[i]) - height[i];

        return ans;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height));
        int[] height2 = {4,2,0,3,2,5};
        System.out.println(trap(height2));
        int[] height3 = {5,4,1,2};
        System.out.println(trap(height3));
        int[] height4 = {0,7,1,4,6};
        System.out.println(trap(height4));
    }

}
