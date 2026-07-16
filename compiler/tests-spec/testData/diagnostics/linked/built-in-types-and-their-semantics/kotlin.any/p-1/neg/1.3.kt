// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.any -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: equals must return Boolean when overriding kotlin.Any.equals
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Case1 {
    override fun equals(other: Any?): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>Int<!> = 1
}


// TESTCASE NUMBER: 2
class Case2 {
    override fun equals(other: Any?): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>String<!> = "x"
}


// TESTCASE NUMBER: 3
class Case3 {
    override fun equals(other: Any?): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>Unit<!> {}
}


// TESTCASE NUMBER: 4
class Case4 {
    override fun equals(other: Any?): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>Any<!> = this
}


// TESTCASE NUMBER: 5
class Case5 {
    override fun equals(other: Any?): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>Boolean?<!> = true
}
