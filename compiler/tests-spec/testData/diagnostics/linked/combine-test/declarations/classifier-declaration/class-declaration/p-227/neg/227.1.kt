// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 227 -> sentence 227
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 227 -> sentence 227
 *                inheritance, overriding -> paragraph 227 -> sentence 227
 * NUMBER: 1
 * DESCRIPTION: a wrong-return-type override of one overload cannot fake-resolve dual-interface members that differ by parameter list (RETURN_TYPE_MISMATCH_ON_OVERRIDE + ABSTRACT_MEMBER_NOT_IMPLEMENTED); contrasts with p-214 correct dual overloads and with p-220 same-signature incompatible returns
 */

// TESTCASE NUMBER: 1
interface IntArg {
    fun f(x: Int): Int
}

interface StringArg {
    fun f(x: String): String
}

<!ABSTRACT_MEMBER_NOT_IMPLEMENTED!>class BadIntReturn<!> : IntArg, StringArg {
    override fun f(x: Int): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>String<!> = ""
}

// TESTCASE NUMBER: 2
interface BoolArg {
    fun g(b: Boolean): Boolean
}

interface LongArg {
    fun g(n: Long): Long
}

<!ABSTRACT_MEMBER_NOT_IMPLEMENTED!>class BadBoolReturn<!> : BoolArg, LongArg {
    override fun g(b: Boolean): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>Int<!> = 0
}

// TESTCASE NUMBER: 3
interface LeftPair {
    fun h(a: Int, b: Int): Int
}

interface RightSingle {
    fun h(a: Int): Int
}

<!ABSTRACT_MEMBER_NOT_IMPLEMENTED!>class BadArityReturn<!> : LeftPair, RightSingle {
    override fun h(a: Int, b: Int): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>String<!> = ""
}
