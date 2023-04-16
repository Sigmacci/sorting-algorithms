import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int A[] = new int[30000];
        Scanner s = new Scanner(System.in);
        Generator generate = new Generator();
        System.out.print("Enter data shape: ");
        String type = s.nextLine();
        switch (type) {
            case "RAND":
                generate.rand(A);
                break;
            case "ASC":
                generate.asc(A);
                break;
            case "DESC":
                generate.desc(A);
                break;
            case "ASH":
                generate.a_shape(A);
                break;
            case "VSH":
                generate.v_shape(A);
                break;
            default:
                generate.rand(A);
        }
        System.out.print("\nEnter sorting algorithm: ");
        String algorithm = s.nextLine();
        switch (algorithm) {
            case "BUBBLE":
                System.out.println("Bubble sort time: " + bubbleTime(A));
                break;
            case "INSERTION":
                System.out.println("Insertion sort time: " + insertionTime(A));
                break;
            case "SELECTION":
                System.out.println("Selection sort time: " + selectionTime(A));
                break;
            case "SHELL":
                System.out.println("Shell sort time: " + shellTime(A));
                break;
            case "HEAP":
                System.out.println("Heap sort time: " + heapTime(A));
                break;
            case "QUICK":
                System.out.println("Quick sort time: " + quickTime(A));
                break;
            case "MERGE":
                System.out.println("Merge sort time: " + mergeTime(A));
                break;
        }
        s.close();
    }

    static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static double insertionTime(int[] arr) {
        InsertionSort is = new InsertionSort();
        Instant start = Instant.now();
        is.sort(arr);
        Instant finish = Instant.now();
        double time = Duration.between(start, finish).toNanos() / 1000000.0;
        return time;
    }

    public static double quickTime(int[] arr) {
        QuickSort qs = new QuickSort();
        Instant start = Instant.now();
        qs.sort(arr, 0, arr.length - 1);
        Instant finish = Instant.now();
        double time = Duration.between(start, finish).toNanos() / 1000000.0;
        return time;
    }

    public static double mergeTime(int[] arr) {
        MergeSort ms = new MergeSort();
        Instant start = Instant.now();
        ms.sort(arr, 0, arr.length - 1);
        Instant finish = Instant.now();
        double time = Duration.between(start, finish).toNanos() / 1000000.0;
        return time;
    }

    public static double selectionTime(int[] arr) {
        SelectionSort ss = new SelectionSort();
        Instant start = Instant.now();
        ss.sort(arr);
        Instant finish = Instant.now();
        double time = Duration.between(start, finish).toNanos() / 1000000.0;
        return time;
    }

    public static double bubbleTime(int[] arr) {
        BubbleSort bs = new BubbleSort();
        Instant start = Instant.now();
        bs.sort(arr);
        Instant finish = Instant.now();
        double time = Duration.between(start, finish).toNanos() / 1000000.0;
        return time;
    }

    public static double shellTime(int[] arr) {
        ShellSort shs = new ShellSort();
        Instant start = Instant.now();
        shs.sort(arr);
        Instant finish = Instant.now();
        double time = Duration.between(start, finish).toNanos() / 1000000.0;
        return time;
    }

    public static double heapTime(int[] arr) {
        HeapSort hs = new HeapSort();
        Instant start = Instant.now();
        hs.sort(arr);
        Instant finish = Instant.now();
        double time = Duration.between(start, finish).toNanos() / 1000000.0;
        return time;
    }
}

class BubbleSort {
    public void sort(int[] arr) {
        int tmp;
        for (int j = 0; j < arr.length - 1; j++) {
            for (int i = 0; i < arr.length - j - 1; i++) {
                if (arr[i] >= arr[i + 1]) {
                    tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                }
            }
        }
    }
}

class InsertionSort {
    public void sort(int[] arr) {
        int i, key;
        for (int j = 0; j < arr.length; j++) {
            key = arr[j];
            i = j - 1;
            while (i >= 0 && arr[i] >= key) {
                arr[i + 1] = arr[i];
                i--;
            }
            arr[i + 1] = key;
        }
    }
}

class SelectionSort {
    public void sort(int[] arr) {
        int min, tmp;
        for (int j = 0; j < arr.length; j++) {
            min = j;
            for (int i = j + 1; i < arr.length; i++) {
                if (arr[min] <= arr[i]) {
                    min = i;
                }
            }
            tmp = arr[min];
            arr[min] = arr[j];
            arr[j] = tmp;
        }
    }
}

class MergeSort {
    private void merge(int arr[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int L[] = new int[n1];
        int R[] = new int[n2];
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] > R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public void sort(int arr[], int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            sort(arr, l, m);
            sort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }
}

class ShellSort {
    public void sort(int[] arr) {
        int tmp;
        for (int k = arr.length / 2; k > 0; k /= 2) {
            for (int i = k; i < arr.length; i++) {
                tmp = arr[i];
                int j = i;
                while (j >= k && arr[j - k] <= tmp) {
                    arr[j] = arr[j - k];
                    j -= k;
                }
                arr[j] = tmp;
            }
        }
    }
}

class QuickSort {
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    public void sort(int[] arr, int low, int high) {
        if (low < high) {
            // System.out.println(arr[high]);
            int pi = partition(arr, low, high);
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }
}

class HeapSort {
    public void sort(int arr[]) {
        int N = arr.length;
        for (int i = N / 2 - 1; i >= 0; i--) {
            heap(arr, N, i);
        }
        for (int i = N - 1; i > 0; i--) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            heap(arr, i, 0);
        }
    }

    private void heap(int arr[], int N, int i) {
        int min = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < N && arr[l] < arr[min]) {
            min = l;
        }
        if (r < N && arr[r] < arr[min]) {
            min = r;
        }
        if (min != i) {
            int tmp = arr[i];
            arr[i] = arr[min];
            arr[min] = tmp;
            heap(arr, N, min);
        }
    }
}

class Generator {
    public void rand(int[] arr) {
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(10 * arr.length);
        }
    }

    public void asc(int[] arr) {
        Random r = new Random();
        int tmp = r.nextInt(10 * arr.length - arr.length);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = tmp;
            tmp++;
        }
    }

    public void desc(int[] arr) {
        Random r = new Random();
        int tmp = r.nextInt(10 * arr.length);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = tmp;
            tmp--;
        }
    }

    public void a_shape(int[] arr) {
        Random r = new Random();
        int tmp = r.nextInt(10 * arr.length);
        for (int i = 0; i < arr.length / 2; i++) {
            arr[i] = tmp;
            tmp += 2;
        }
        for (int i = arr.length / 2; i < arr.length; i++) {
            arr[i] = tmp;
            tmp -= 3;
        }
    }

    public void v_shape(int[] arr) {
        Random r = new Random();
        int tmp = r.nextInt(10 * arr.length);
        for (int i = 0; i < arr.length / 2; i++) {
            arr[i] = tmp;
            tmp -= 2;
        }
        for (int i = arr.length / 2; i < arr.length; i++) {
            arr[i] = tmp;
            tmp += 3;
        }
    }
}
