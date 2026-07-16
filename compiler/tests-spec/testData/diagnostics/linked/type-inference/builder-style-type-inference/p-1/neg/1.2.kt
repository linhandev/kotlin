// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT
// WITH_STDLIB

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, builder-style-type-inference -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: builder-style type inference — multiple builder lambdas require BuilderInference annotation on each
 * HELPERS: checkType
 */

fun <D> buildPart145(left: MutableList<D>.() -> Unit, right: MutableList<D>.() -> Unit): List<D> {
    val list = mutableListOf<D>()
    list.left()
    list.right()
    return list
}

// TESTCASE NUMBER: 1
fun case_1() {
    buildPart145(
        <!BUILDER_INFERENCE_MULTI_LAMBDA_RESTRICTION!>left = { add(1) }<!>,
        <!BUILDER_INFERENCE_MULTI_LAMBDA_RESTRICTION!>right = { add("") }<!>
    )
}
