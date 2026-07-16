// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: default parameters at runtime
 */

// TESTCASE NUMBER: 1
fun bar(a: Int = 1, b: Double = 42.0, s: String = "13"): Double = a + b + s.toDouble()

fun box(): String {
    val allDefaults = bar()
    val partial = bar(2, s = "5")
    return if (allDefaults == 56.0 && partial == 49.0) "OK" else "NOK $allDefaults $partial"
}
