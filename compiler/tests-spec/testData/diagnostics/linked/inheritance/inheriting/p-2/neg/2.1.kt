// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, inheriting -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: PropConflict532 with value from PropA532 and PropB532 reports MANY_INTERFACES_MEMBER_NOT_IMPLEMENTED
 */

// TESTCASE NUMBER: 1
interface PropA532 {
    val value: Int
        get() = 1
}

interface PropB532 {
    val value: Int
        get() = 2
}

<!MANY_INTERFACES_MEMBER_NOT_IMPLEMENTED!>class PropConflict532<!> : PropA532, PropB532
