// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: extension functions behave like non-extension functions except for receiver dispatch
 */

// TESTCASE NUMBER: 1
fun Int.foo(): Int = this + 1

fun callExtension(): Int = 2.foo()

// TESTCASE NUMBER: 2
class Bar {
    fun memberFoo(): String = "member"

    fun Int.extensionFoo(): String = "extension:$this"

    fun useMember(): String = memberFoo()

    fun useExtension(): String = 3.extensionFoo()
}

// TESTCASE NUMBER: 3
fun Int.timesTwo(): Int = this * 2

fun Int.timesTwo(offset: Int): Int = this * 2 + offset

fun overloadExtension(): Int = 4.timesTwo() + 5.timesTwo(1)
