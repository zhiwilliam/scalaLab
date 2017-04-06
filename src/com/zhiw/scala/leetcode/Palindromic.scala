package com.zhiw.scala.leetcode
import scala.util.control.Breaks._

/**
 * 解体心得： 回文的题目都需要分治。先设计出最简单的检查回文的function，然后再考虑对字符串的遍历，这样会比较容易。
 */
object Palindromic extends App {
    /**
     * Question 5:
     *
     * Find the Longest Palindromic Substring
     *
     * For example, "dabcba" => "abcba"
     *
     * Reference to Question 9: Palindrome Number
     */
    private def max(x: Int, y: Int) = if (x < y) y else x
    def validate(testString: String, i: Int, j: Int): Boolean = {
        if (i == j) true
        else validate(testString.substring(i, j + 1))
    }

    private def longest(testString: String, i: Int, j: Int): Int = {
        if (i == j) 1
        else if (testString.charAt(i) == testString.charAt(j) && i + 1 == j) 2
        else if (testString.charAt(i) == testString.charAt(j)) if (validate(testString, i + 1, j - 1)) j - i + 1 else 0
        else max(longest(testString, i + 1, j), longest(testString, i, j - 1))
    }
    def longest(testString: String): Int = longest(testString, 0, testString.length() - 1)
    //println(longest("ttsabaabacd"))

    /**
     * Question 125: Valid Palindrome
     */
    def validate(testString: String): Boolean = {
        println("validate " + testString)
        val list = testString.toCharArray()
        def check(list: Array[Char]): Boolean = list match {
            case Array.emptyCharArray => true
            case Array(x)             => true
            case Array(x, y)          => x == y
            case x                    => x(0) == x(x.length - 1) && check(x.drop(1).dropRight(1))
        }
        check(list)
    }
    //println(validate("12344321"))

    /**
     * Question 336: Palindrome Pairs
     * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
     *
     * Example 1:
     *   Given words = ["bat", "tab", "cat"]
     *   Return [[0, 1], [1, 0]]
     *   The palindromes are ["battab", "tabbat"]
     *   Example 2:
     *   Given words = ["abcd", "dcba", "lls", "s", "sssll"]
     *   Return [[0, 1], [1, 0], [3, 2], [2, 4]]
     *   The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
     */
    def pairs(list: List[String]) = {
        for (
            i <- List.range(0, list.length);
            j <- List.range(0, list.length) if (i != j && validate(list(i) + list(j)))
        ) yield i :: j :: Nil
    }
//    println(pairs("abcd" :: "dcba" :: "lls" :: "s" :: "sssll" :: Nil))

    /**
     * Question 131: Palindrome Partitioning
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     *
     * Return all possible palindrome partitioning of s.
     *
     * For example, given s = "aab",
     * Return
     *
     * [
     *   ["aa","b"],
     *   ["a","a","b"]
     * ]
     */
    /**
     * idea: it should be a tree structure. bottom line is a,a,b, then a,a has parent aa, a,b has parent ab. out put deepest first, then check parents and so on.
     */

    /**
     * Question 214: Shortest Palindrome
     * DescriptionSubmissionsSolutions
     * Total Accepted: 36948
     * Total Submissions: 157128
     * Difficulty: Hard
     * Contributor: LeetCode
     * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.
     *
     * For example:
     *
     * Given "aacecaaa", return "aaacecaaa".
     *
     * Given "abcd", return "dcbabcd".
     */

    private def shortest(testString: String, i: Int, j: Int): Int = {
        if (i == j) j
        else if (testString.charAt(i) == testString.charAt(j) && i + 1 == j) j
        else if (testString.charAt(i) == testString.charAt(j)) if (validate(testString)) j - i + 2 else shortest(testString, i, j - 1)
        else shortest(testString, i, j - 1)
    }

    private def shortest(testString: String): String = {
        val len = shortest(testString, 0, testString.length() - 1)
        val append = testString.substring(len).reverse
        append + testString
    }
    //println(shortest("abccba2ddcba"))
}