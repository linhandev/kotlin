// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT
// WITH_STDLIB

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, builder-style-type-inference -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: builder-style type inference — using postponed receiver type in expression is a compile-time error
 * HELPERS: checkType
 */

fun <R> buildList145(lambda: MutableList<R>.() -> Unit): List<R> = emptyList()

fun <T> T.ext145() {}

// TESTCASE NUMBER: 1
fun case_1() {
    <!INFERRED_INTO_DECLARED_UPPER_BOUNDS!>buildList145<!> {
        val v = get(0)
        <!BUILDER_INFERENCE_STUB_RECEIVER!>v<!>.ext145()
    }
}
