// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: abstract sealed RedundantAbstract512; sealed fun interface; object and local Base512 subclass report REDUNDANT_MODIFIER, UNSUPPORTED_SEALED_FUN_INTERFACE and SEALED_SUPERTYPE
 */

// TESTCASE NUMBER: 1
<!REDUNDANT_MODIFIER!>abstract<!> sealed class RedundantAbstract512

<!UNSUPPORTED_SEALED_FUN_INTERFACE!>sealed<!> fun interface SealedFunInterface512 {
    fun invoke(value: Int): Int
}

sealed class Base512

fun case1() {
    val anon = object : <!SEALED_SUPERTYPE!>Base512<!>() {}
    class Local : <!SEALED_SUPERTYPE!>Base512<!>()
}
