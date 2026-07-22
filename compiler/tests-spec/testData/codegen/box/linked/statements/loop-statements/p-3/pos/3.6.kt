// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, loop-statements -> paragraph 3 -> sentence 3
 * NUMBER: 6
 * DESCRIPTION: for (v in custom IntRange(1..3)) uses operator iterator() and sums to 6
 */

class IntRange(private val from: Int, private val to: Int) {
    private class IteratorImpl(private var current: Int, private val to: Int) : Iterator<Int> {
        override fun hasNext(): Boolean = current <= to
        override fun next(): Int {
            if (!hasNext()) throw NoSuchElementException()
            return current++
        }
    }

    operator fun iterator(): Iterator<Int> = IteratorImpl(from, to)
}

// TESTCASE NUMBER: 1
fun box(): String {
    var sum = 0
    for (v in IntRange(1, 3)) {
        sum += v
    }
    return if (sum == 6) "OK" else "NOK"
}
