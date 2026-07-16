// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declarations-with-type-parameters -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: generic class, generic function, and where-bounded class compile and are usable in expressions
 */

// TESTCASE NUMBER: 1
class Box<T>(val value: T)

// TESTCASE NUMBER: 2
fun <T> id(x: T): T = x

// TESTCASE NUMBER: 3
class SortedBox<T>(val value: T) where T : Comparable<T>

fun useGenerics(): Boolean {
    val box = Box(1)
    val same = id(box.value)
    val sorted = SortedBox("ok")
    return same == 1 && sorted.value == "ok"
}
