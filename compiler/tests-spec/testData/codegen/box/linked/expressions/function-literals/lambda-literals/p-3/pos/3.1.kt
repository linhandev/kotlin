// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, function-literals, lambda-literals -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: lambda destructuring parameter list
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val plus: (Pair<Int, Double>) -> String = { (i, d) ->
        "$i + $d = ${i + d}"
    }
    if (plus(1 to 2.0) != "1 + 2.0 = 3.0") return "NOK"
    return "OK"
}
