// FIR_IDENTICAL
// LANGUAGE: +FunctionalInterfaceConversion
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, interface-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: fun interface must have exactly one abstract member and cannot declare abstract properties
 */

// TESTCASE NUMBER: 1
<!FUN_INTERFACE_WRONG_COUNT_OF_ABSTRACT_MEMBERS!>fun<!> interface Empty

// TESTCASE NUMBER: 2
<!FUN_INTERFACE_WRONG_COUNT_OF_ABSTRACT_MEMBERS!>fun<!> interface TwoMethods {
    fun foo()
    fun bar()
}

// TESTCASE NUMBER: 3
<!FUN_INTERFACE_WRONG_COUNT_OF_ABSTRACT_MEMBERS!>fun<!> interface WithAbstractProperty {
    <!FUN_INTERFACE_CANNOT_HAVE_ABSTRACT_PROPERTIES!>val<!> prop: Int
}
