// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, kotlin-nothing -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: code after call to function returning kotlin.Nothing is unreachable
 */

fun never1215(): Nothing = throw IllegalStateException()

// TESTCASE NUMBER: 1
fun case_1() {
    never1215()
    println("after")
}
