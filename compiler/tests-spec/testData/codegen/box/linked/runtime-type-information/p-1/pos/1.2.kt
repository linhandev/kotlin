// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: runtime-type-information -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: anonymous object literal has classifier runtime type at runtime
 */
// TESTCASE NUMBER: 1

interface AnonMarker

fun box(): String {
    val o = object : AnonMarker {
        val tag = listOf(1, 2, 3).sum()
    }
    if (o.tag != 6) return "NOK: expected tag=6 (1+2+3), got ${o.tag}"
    if (o !is AnonMarker) return "NOK: anonymous object should have classifier runtime type"
    return "OK"
}
