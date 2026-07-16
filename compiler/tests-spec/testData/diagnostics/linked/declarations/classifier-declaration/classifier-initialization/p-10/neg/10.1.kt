// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-initialization -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: throw-only expression body infers Nothing return type; abstract function with body in concrete class is rejected
 */

// TESTCASE NUMBER: 1
fun <!IMPLICIT_NOTHING_RETURN_TYPE!>throwsOnly<!>() = throw IllegalStateException()

// TESTCASE NUMBER: 2
class C {
    <!ABSTRACT_FUNCTION_IN_NON_ABSTRACT_CLASS, ABSTRACT_FUNCTION_WITH_BODY!>abstract<!> fun bodyAndAbstract() {}
}
