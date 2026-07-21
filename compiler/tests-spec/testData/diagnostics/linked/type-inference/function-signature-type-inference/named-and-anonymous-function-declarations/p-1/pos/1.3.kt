// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, function-signature-type-inference, named-and-anonymous-function-declarations -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: expression body — anonymous function return type inferred from initializer
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val anon = fun(): String = "ok"
    checkSubtype<String>(anon())
}
