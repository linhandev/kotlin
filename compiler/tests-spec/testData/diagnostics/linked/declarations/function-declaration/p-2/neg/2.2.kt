// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: missing function body in non-abstract class member
 */

// TESTCASE NUMBER: 1
class Service {
    <!NON_ABSTRACT_FUNCTION_WITH_NO_BODY!>fun work()<!>
}
