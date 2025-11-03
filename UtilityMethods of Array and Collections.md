# Java Collection Utilities

Java provides two important utility classes for working with data structures:

- **`java.util.Collections`** → For `List`, `Set`, `Map`, etc.
- **`java.util.Arrays`** → For working with primitive and object arrays.

These classes contain **static methods** that make your code faster, cleaner, and easier to maintain.

---

##  `java.util.Collections`

A utility class with static methods that operate on or return collections.

---

###  Commonly Used Methods

| **Method** | **Description** | **Example** |
|-------------|-----------------|--------------|
| `sort(List list)` | Sorts list in natural order | `Collections.sort(names);` |
| `sort(List, Comparator<? super T>)` | Sorts using custom comparator | `Collections.sort(employees, Comparator.comparing(Employee::getName));` |
| `reverse(List<?>)` | Reverses list elements | `Collections.reverse(list);` |
| `shuffle(List<?>)` | Randomly rearranges elements | `Collections.shuffle(cards);` |
| `swap(List<?>, int i, int j)` | Swaps two elements | `Collections.swap(list, 0, 2);` |
| `fill(List<? super T>, T obj)` | Replaces all with given object | `Collections.fill(list, "N/A");` |
| `copy(List<? super T> dest, List<? extends T> src)` | Copies elements from source to destination | `Collections.copy(dest, src);` |
| `frequency(Collection<?>, Object o)` | Counts occurrences of an element | `Collections.frequency(list, "Java");` |
| `replaceAll(List, T oldVal, T newVal)` | Replaces all occurrences | `Collections.replaceAll(list, "A", "B");` |
| `max(Collection<? extends T>)` | Returns max element | `Collections.max(list);` |
| `min(Collection<? extends T>)` | Returns min element | `Collections.min(list);` |
| `binarySearch(List<? extends Comparable<? super T>>, T key)` | Searches element in sorted list | `Collections.binarySearch(list, "Spring");` |
| `disjoint(Collection<?> c1, Collection<?> c2)` | True if no elements in common | `Collections.disjoint(list1, list2);` |

---

### Example

```java
import java.util.*;

public class CollectionsExample {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(Arrays.asList("Rashmi", "Kiran", "Anu"));

        Collections.sort(names);
        System.out.println("Sorted: " + names);

        Collections.reverse(names);
        System.out.println("Reversed: " + names);

        Collections.shuffle(names);
        System.out.println("Shuffled: " + names);

        int freq = Collections.frequency(names, "Rashmi");
        System.out.println("Frequency of Rashmi: " + freq);
    }
}

```

# `java.util.Arrays` — Java Array Utility Class

The `java.util.Arrays` class is a **utility class** in Java that provides many helpful static methods to work with arrays — both **primitive arrays** (like `int[]`) and **object arrays** (like `String[]`).

---

## Why Use `Arrays`

Working directly with arrays can be limited — you can’t easily:
- Print arrays in human-readable format
- Resize them
- Search or compare easily

The `Arrays` class gives you one-liners for all of these!

---

##  Commonly Used Methods (with Examples)

Below are the most useful methods from `java.util.Arrays`, with full examples for each.

---

### 1️⃣ `Arrays.toString()`

Converts an array into a readable string.

```java
import java.util.Arrays;

public class ToStringExample {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40};
        System.out.println(Arrays.toString(arr));
    }
}
// Output: [10, 20, 30, 40]

```

### 2️⃣ `Arrays.sort()`
Sorts the array in ascending order(default) or descending order.

```java 
public static void main(String[] args) {
    int[] arr = {30, 10, 40, 20};
    // Sort the array in ascending order
    Arrays.sort(arr);
    System.out.println(Arrays.toString(arr));

    //Sort the array in descending order
    arr = Arrays.stream(arr) // convert to IntStream - a stream that supports only primitive int values (not objects)
            .boxed()        //Converts IntStream → Stream<Integer> because Collections.reverseOrder() 
            // works only with objects that implement Comparable (like Integer), not primitive int
            .sorted(Collections.reverseOrder()) // sort descending using Comparator
            .mapToInt(Integer::intValue) // convert Stream<Integer> back to IntStream (unbox)
            .toArray(); // convert to int[]

    // You can also sort object arrays:
    String[] fruits = {"Banana", "Apple", "Mango"};
    Arrays.sort(fruits);
    System.out.println(Arrays.toString(fruits));
}
// Output: [10, 20, 30, 40]
// Output: [Apple, Banana, Mango]
```

### 3️⃣ `Arrays.parallelSort()'
Sorts the array using parallel processing for large arrays(uses multiple threads).

```java
public static void main(String[] args) {
    int[] arr = {30, 10, 40, 20};
    Arrays.parallelSort(arr);
    System.out.println("Sort in asending order :" + Arrays.toString(arr));
    arr = Arrays.stream(arr)
            .parallel() // enable parallel processing if needed
            .boxed()
            .sorted(Collections.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();
    System.out.println("Sorted in descending order: " + Arrays.toString(arr));
}

// Output: Sort in ascending order :[10, 20, 30, 40]
// Output: Sorted in descending order: [40, 30, 20, 10]
```

### 4️⃣ `Arrays.binarySearch()`
BinarySearch is an algorithm for finding the position of a specific value within a sorted array. 
Searches for a specific value in a sorted array.

```java
public static void main(String[] args) {
    int[] arr = {10, 20, 30, 40, 50};
    int index = Arrays.binarySearch(arr, 30);
    System.out.println("Index of 30: " + index);
}
// Output: Index of 30: 2
```

### 5️⃣ `Arrays.copyOf()` and `Arrays.copyOfRange()`
Creates a new array by copying elements from an existing array.
We can copy the part of an array using `copyOfRange()`.

```java
public static void main(String[] args) {
    int[] original = {10, 20, 30, 40, 50};
    int[] copy = Arrays.copyOf(original, 3); // copies first 3 elements
    System.out.println("Copy: " + Arrays.toString(copy));

    int[] rangeCopy = Arrays.copyOfRange(original, 1, 4); // copies elements from index 1 to 3
    System.out.println("Range Copy: " + Arrays.toString(rangeCopy));

    
    int[] arr = {1, 2, 3, 4};

    int[] copy = Arrays.copyOf(arr, 6);
    System.out.println(Arrays.toString(copy));
    // Output: [1, 2, 3, 4, 0, 0]

    int[] range = Arrays.copyOfRange(arr, 1, 3);
    System.out.println(Arrays.toString(range));
    // Output: [2, 3]
}
// Output: Copy: [10, 20, 30]
// Output: Range Copy: [20, 30, 40]
```

### Understanding each method
| Method                          | Type of Copy        | Behavior                                            | Example Result       |
| ------------------------------- | ------------------- | --------------------------------------------------- | -------------------- |
| `Arrays.copyOf(arr, 6)`         | Full copy (extended) | Copies whole array, fills extra with default values | `[1, 2, 3, 4, 0, 0]` |
| `Arrays.copyOfRange(arr, 1, 3)` | Partial copy      | Copies only elements from index `1` to `2`          | `[2, 3]`             |

### 6️⃣ `Arrays.fill()`
Fills an array with a specific value.
```java
public static void main(String[] args) {
    int[] arr = new int[5];
    Arrays.fill(arr, 42);
    System.out.println("Filled Array: " + Arrays.toString(arr));
}
// Output: Filled Array: [42, 42, 42, 42, 42]
```

### 7️⃣ `Arrays.equals()` and `Arrays.deepEquals()`
Compares two arrays for equality. `deepEquals` is used for nested arrays.
```java
public static void main(String[] args) {
   //One-dimensional array comparison
   int[] arr1 = {1,2,3};
   int[] arr2 = {1,2,3};
    System.out.println("Arrays equal: " + Arrays.equals(arr1, arr2));
    
    //Two-dimensional array comparison
    int[][] arr3 = {{1,2}, {3,4}};
    int[][] arr4 = {{1,2}, {3,4}};
    System.out.println("Deep Arrays equal: " + Arrays.deepEquals(arr3, arr4));
}
// Output: Arrays equal: true
// Output: Deep Arrays equal: true
```

### 8️⃣ `Arrays.asList()`
Converts an array to a fixed-size list.
```java
public static void main(String[] args) {
    String[] arr = {"Java", "Python", "C++"};
    system.out.println("Array: " + Arrays.toString(arr));
    List<String> list = Arrays.asList(arr);
    System.out.println("List: " + list);
}
// Output: List: [Java, Python, C++]
//Note: The returned list is fixed-size. You can’t add or remove elements.
```

### 9️⃣ `Arrays.stream()`
Convert an array into a Stream for functional-style operations.
```java
public static void main(String[] args) {
    int[] arr = {10,20,30,40,50};
    int sum = Arrays.stream(arr)
    .filter(num -> num < 20)
    .sum();
    System.out.println("Sum of elements < 20: " + sum);
}
//Output: Sum of elements < 20: 10
```

