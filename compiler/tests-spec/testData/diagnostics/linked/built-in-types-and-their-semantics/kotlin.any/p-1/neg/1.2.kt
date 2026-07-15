// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.any -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: hashCode and toString must match kotlin.Any member signatures
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Case1 {
    override fun hashCode(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>Boolean<!> = true
}


// TESTCASE NUMBER: 2
class Case2 {
    override fun hashCode(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>String<!> = "x"
}


// TESTCASE NUMBER: 3
class Case3 {
    override fun toString(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>Int<!> = 1
}


// TESTCASE NUMBER: 4
class Case4 {
    override fun toString(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>Any<!> = "x"
}


// TESTCASE NUMBER: 5
class Case5 {
    <!NOTHING_TO_OVERRIDE!>override<!> fun hashCode(x: Int): Int = x
    <!NOTHING_TO_OVERRIDE!>override<!> fun toString(prefix: String): String = prefix
}
