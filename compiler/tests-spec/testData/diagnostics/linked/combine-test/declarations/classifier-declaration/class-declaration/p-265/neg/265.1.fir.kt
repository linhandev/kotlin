// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 265 -> sentence 265
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 265 -> sentence 265
 *                declarations, property-declaration -> paragraph 265 -> sentence 265
 * NUMBER: 1
 * DESCRIPTION: private class members are invisible outside the declaring class (INVISIBLE_REFERENCE); covers primary-constructor property, member function, and body property; contrasts with p-68 constructor-focused single case and with next-point inside-class access
 */

// TESTCASE NUMBER: 1
class Vault(private val secret: Int = 1)

fun case1(): Int = Vault().<!INVISIBLE_REFERENCE!>secret<!>

// TESTCASE NUMBER: 2
class Lock {
    private fun code(): Int = 2
}

fun case2(): Int = Lock().<!INVISIBLE_REFERENCE!>code<!>()

// TESTCASE NUMBER: 3
class Capsule {
    private val hidden: String = "x"
}

fun case3(): String = Capsule().<!INVISIBLE_REFERENCE!>hidden<!>
