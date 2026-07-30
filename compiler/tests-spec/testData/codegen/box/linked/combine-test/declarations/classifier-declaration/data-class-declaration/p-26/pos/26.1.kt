// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: Map.Entry destructuring uses componentN like data classes
 */

// TESTCASE NUMBER: 1
fun test(): Int {
    val e: Map.Entry<String, Int> = mapOf("a" to 1).entries.first()
    val (k, v) = e
    return k.length + v
}

fun box(): String {
    if (test() != 2) return "NOK"
    return "OK"
}
