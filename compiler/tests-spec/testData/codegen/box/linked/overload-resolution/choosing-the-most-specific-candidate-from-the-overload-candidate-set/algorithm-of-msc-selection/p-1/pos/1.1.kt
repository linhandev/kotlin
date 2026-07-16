/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, choosing-the-most-specific-candidate-from-the-overload-candidate-set, algorithm-of-msc-selection -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: candidate with fewer unspecified default parameters is more specific
 */

fun pick11402D(a: Int, b: String = "d"): String = "few"

fun pick11402D(a: Int, b: String = "d", c: Int = 0): String = "many"

// TESTCASE NUMBER: 1
fun box(): String = if (pick11402D(1) == "few") "OK" else "NOK"
