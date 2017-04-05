package com.zhiw.scala.sorts

object MergeSort extends App {
    // curry function. First parameter is compare function. xs is the stream list.
    def mergeSort[T](pred: (T, T) => Boolean)(xs: Stream[T]): Stream[T] = {
        // half it.
        val m = xs.length / 2
        if (m == 0) xs
        else {
            def merge(ls: Stream[T], rs: Stream[T]): Stream[T] = (ls, rs) match {
                case (Stream.Empty, _) => rs // if left side is empty, return right side
                case (_, Stream.Empty) => ls // if right side is empty, return left side.
                case (l #:: ls1, r #:: rs1) => // if left side and right are not empty, sort the heads only and do merge continuing.
                    if (pred(l, r)) l #:: merge(ls1, rs)
                    else r #:: merge(ls, rs1)
            }
            val (l, r) = xs splitAt m // Split from middle
            merge(mergeSort(pred)(l), mergeSort(pred)(r))
        }
    }

    def numbers(remain: Int): Stream[Int] =
        if (remain == 0) Stream.Empty
        else Stream.cons(util.Random.nextInt(100), numbers(remain - 1))

    println(mergeSort((x: Int, y: Int) => x < y)(numbers(4)).toList)

    // Below is tailrec version.
    implicit def IntIntLessThan(x: Int, y: Int) = x < y

    def mergeSort[T](xs: List[T])(implicit pred: (T, T) => Boolean): List[T] = {
        val m = xs.length / 2
        if (m == 0) xs
        else {
            @scala.annotation.tailrec
            def merge(ls: List[T], rs: List[T], acc: List[T] = List()): List[T] = (ls, rs) match {
                case (Nil, _) => acc ++ rs
                case (_, Nil) => acc ++ ls
                case (l :: ls1, r :: rs1) =>
                    if (pred(l, r)) merge(ls1, rs, acc :+ l)
                    else merge(ls, rs1, acc :+ r)
            }
            val (l, r) = xs splitAt m
            merge(mergeSort(l), mergeSort(r))
        }
    }

    println(mergeSort(List(4, 2, 1, 3)))
}