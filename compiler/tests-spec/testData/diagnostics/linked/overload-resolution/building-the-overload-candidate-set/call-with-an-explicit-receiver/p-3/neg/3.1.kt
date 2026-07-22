// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, building-the-overload-candidate-set, call-with-an-explicit-receiver -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: explicit super-form receiver call fails when member is missing on supertype
 */

open class Base11203SN

class Leaf11203SN : Base11203SN() {
    // TESTCASE NUMBER: 1
    fun case_1(): Int = super.<!UNRESOLVED_REFERENCE!>missing11203SN<!>()
}
