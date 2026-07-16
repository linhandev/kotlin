// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: matching function signatures allow override; differing signatures allow overload
 */

// TESTCASE NUMBER: 1
open class Base {
    open fun transform(x: Int): String = x.toString()
}

class Derived : Base() {
    override fun transform(x: Int): String = (x + 1).toString()
}

// TESTCASE NUMBER: 2
fun overload(x: Int): Int = x
fun overload(x: String): Int = x.length

// TESTCASE NUMBER: 3
open class GenericBase {
    open fun <T> identity(value: T): T = value
}

class GenericDerived : GenericBase() {
    override fun <T> identity(value: T): T = value
}
