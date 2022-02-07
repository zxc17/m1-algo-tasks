package practicum;

import java.util.*;

public class Algorithms {

    /**
     * Отсортируйте список, НЕ используя методы стандартной библиотеки (напр. Collections.sort).
     */
    public static List<Integer> sort(List<Integer> list) {
        Integer[] sorted = list.toArray(new Integer[0]);

        for (int i = 0; i < sorted.length - 1; i++) {
            for (int j = i; j < sorted.length; j++) {
                if (sorted[j] < sorted[i]) {
                    int tmp = sorted[i];
                    sorted[i] = sorted[j];
                    sorted[j] = tmp;
                }
            }
        }
        return Arrays.asList(sorted);
    }

    /**
     * Удалите дубликаты из списка.
     * <p>
     * Усложнение: не используйте дополнительные структуры данных
     * для хранения промежуточных значений.
     * (списки, массивы, хэш-таблицы, множества и т.п.).
     * К списку-результату это не относится.
     */
    public static List<Integer> removeDuplicates(List<Integer> list) {
        List<Integer> result = new ArrayList<>(list);
        Iterator<Integer> iter = result.iterator();
        int n = 0;
        while (iter.hasNext()) {
            Integer cur = iter.next();
            boolean isDup = false;
            for (int i = 0; i < n; i++) {
                if (cur == result.get(i)) {
                    isDup = true;
                    break;
                }
            }
            if (isDup)
                iter.remove();
            else
                n++;
        }
        return result;
    }

    /**
     * Проверьте, является ли список "палиндромом".
     * Палиндром -- это список, который в обе стороны читается одинаково.
     * Например:
     * палиндромы: [1 2 1], [3 2 1 2 3], [2 2 2], []
     * не палиндромы: [1 2 3], [2 2 3], [3 2 1 3 2]
     * <p>
     * Доп. условие: у алгоритма должна быть линейная сложность, O(n)
     */
    public static boolean isPalindrome(List<Integer> list) {
        int size = list.size();
        int middle = size / 2;
        for (int i = 0; i < middle; i++) {
            if (list.get(i) != list.get(size - i - 1))
                return false;
        }
        return true;
    }

    /**
     * Объедините два отсортированных списка в один отсортированный список.
     * Например:
     * [1 3 5] + [2 4 6] = [1 2 3 4 5 6]
     * [1 2 3] + [1 3 5] = [1 1 2 3 3 5]
     * [] + [1] = [1]
     * [7] + [1 4] = [1 4 7]
     * <p>
     * Доп. условие: у алгоритма должна быть линейная сложность, O(n).
     */
    public static List<Integer> mergeSortedLists(List<Integer> a, List<Integer> b) {
        List<Integer> sorted = new ArrayList<>();
        Iterator<Integer> iterA = a.iterator();
        Iterator<Integer> iterB = b.iterator();
        Integer nA, nB;

        if (a.size() == 0)
            return b;
        if (b.size() == 0)
            return a;
        nA = iterA.next();
        nB = iterB.next();
        while (true) {
            if (nA < nB) {
                sorted.add(nA);
                if (iterA.hasNext())
                    nA = iterA.next();
                else {
                    sorted.add(nB);
                    while (iterB.hasNext())
                        sorted.add(iterB.next());
                    break;
                }
            } else {
                sorted.add(nB);
                if (iterB.hasNext())
                    nB = iterB.next();
                else {
                    sorted.add(nA);
                    while (iterA.hasNext())
                        sorted.add(iterA.next());
                    break;
                }
            }
        }
        return sorted;
    }

    /**
     * Проверьте, что в массиве нет дубликатов.
     * Верните true, если дубликатов нет, иначе false.
     * <p>
     * Усложнение: не используйте дополнительные структуры данных
     * (списки, массивы, хэш-таблицы, множества и т.п.).
     */
    public static boolean containsEveryElementOnce(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] == array[j])
                    return false;
            }
        }
        return true;
    }

    /**
     * Определите, является ли один массив перестановкой другого.
     * Т.е. в массивах хранится один и тот же набор элементов, но (возможно) в разном порядке.
     * <p>
     * Для решения нжуно использовать одну хэш-таблицу.
     * <p>
     * Например:
     * [1 2 3] и [3 2 1] = true
     * [1 1 2] и [1 2 1] = true
     * [1 2 3] и [1 2 3] = true
     * [] и [] = true
     * <p>
     * [1 2] и [1 1 2] = false, разный набор элементов
     */
    public static boolean isPermutation(int[] a, int[] b) {
        if (a.length == 0 && b.length == 0) return true;
        if (a.length != b.length) return false;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            map.put("" + i, a[i]);
        }

        for (int n : b) {
            var iter = map.values().iterator();
            while (iter.hasNext()) {
                if (iter.next() == n) {
                    iter.remove();
                    break;          // Если таких элементов несколько, то удалить надо только один.
                }
            }
        }
        return map.isEmpty();
    }

    /**
     * Сложная задача.
     * <p>
     * В памяти компьютера изображения (часто) хранятся в виде двумерного массива.
     * Напишите метод, который повернёт "изображение" на 90 градусов вправо.
     * "Изображение" в данном примере -- двумерный массив целых чисел.
     * <p>
     * Например:
     * на входе:
     * [ [1 2]
     * [3 4]
     * [5 6] ]
     * <p>
     * на выходе:
     * [ [5 3 1]
     * [6 4 2] ]
     */
    public static int[][] rotateRight(int[][] image) {
        int columns = image[0].length;
        int rows = image.length;
        int[][] result = new int[columns][rows];

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                result[x][rows - y - 1] = image[y][x];
            }
        }
        return result;
    }
}
