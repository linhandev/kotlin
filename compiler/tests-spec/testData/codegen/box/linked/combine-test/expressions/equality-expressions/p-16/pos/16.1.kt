// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: expressions, equality-expressions, reference-equality-expressions -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: distinct buildString instances: == true, === false; same ref === true
 */

// TESTCASE NUMBER: 1
fun s(x: String): String = buildString { append(x) }

fun structuralEqual(): Boolean {
    val a = s("a")
    val b = s("a")
    return a == b
}

fun referentialEqual(): Boolean {
    val a = s("a")
    val b = s("a")
    return a === b
}

fun sameReference(): Boolean {
    val a = s("a")
    return a === a
}

fun box(): String {
    if (!structuralEqual()) return "NOK: =="
    if (referentialEqual()) return "NOK: === distinct"
    if (!sameReference()) return "NOK: === same"
    return "OK"
}
