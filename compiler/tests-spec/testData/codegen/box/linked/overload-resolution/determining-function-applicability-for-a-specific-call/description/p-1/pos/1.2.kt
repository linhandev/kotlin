/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, determining-function-applicability-for-a-specific-call, description -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: declaration-site type constraints satisfied for applicable call
 */

fun <T : Number> pick11302G(t: T): String = "num:$t"

// TESTCASE NUMBER: 1
fun box(): String = if (pick11302G(42) == "num:42") "OK" else "NOK"
