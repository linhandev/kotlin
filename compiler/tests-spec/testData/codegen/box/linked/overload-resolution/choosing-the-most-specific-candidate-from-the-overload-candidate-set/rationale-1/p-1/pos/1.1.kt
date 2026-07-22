/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, choosing-the-most-specific-candidate-from-the-overload-candidate-set, rationale-1 -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: more specific overload Int,String is chosen over Any?,CharSequence for f(2, Hello)
 */

fun pick11401(arg: Int, arg2: String): String = "specific"

fun pick11401(arg: Any?, arg2: CharSequence): String = "general"

// TESTCASE NUMBER: 1
fun box(): String = if (pick11401(2, "Hello") == "specific") "OK" else "NOK"
