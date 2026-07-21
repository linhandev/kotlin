// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, overriding -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: override fun fixed and override val fixed on non-open members report OVERRIDING_FINAL_MEMBER
 */

// TESTCASE NUMBER: 1
open class FinalBase540 {
    fun fixed(): Int = 1
}

class BadFinal540 : FinalBase540() {
    <!OVERRIDING_FINAL_MEMBER!>override<!> fun fixed(): Int = 2
}

// TESTCASE NUMBER: 2
open class NonOpenProp540 {
    val fixed: Int get() = 1
}

class BadNonOpen540 : NonOpenProp540() {
    <!OVERRIDING_FINAL_MEMBER!>override<!> val fixed: Int get() = 2
}
