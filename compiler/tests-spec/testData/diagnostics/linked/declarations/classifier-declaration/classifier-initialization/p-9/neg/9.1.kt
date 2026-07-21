// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-initialization -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: non-abstract class member without body is rejected; abstract member in non-abstract class is rejected
 */

// TESTCASE NUMBER: 1
class C {
    <!NON_ABSTRACT_FUNCTION_WITH_NO_BODY!>fun missingBody()<!>
}

// TESTCASE NUMBER: 2
class D {
    <!ABSTRACT_FUNCTION_IN_NON_ABSTRACT_CLASS!>abstract<!> fun illegalAbstract()
}
