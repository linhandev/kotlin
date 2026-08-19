// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 120 -> sentence 120
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 120 -> sentence 120
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 120 -> sentence 120
 * NUMBER: 1
 * DESCRIPTION: property initializers run before init blocks and in source order relative to later properties in class declaration
 */

// TESTCASE NUMBER: 1
class Seq {
    val log = StringBuilder().apply { append("A") }

    init {
        log.append("B")
    }

    val tail = buildString { append("C") }
}

fun combined(): String {
    val instance = Seq()
    return instance.log.toString() + instance.tail
}

fun logPart(): String = Seq().log.toString()

fun tailPart(): String = Seq().tail

fun box(): String {
    if (combined() != "ABC") return "NOK: combined"
    if (logPart() != "AB") return "NOK: log"
    if (tailPart() != "C") return "NOK: tail"
    return "OK"
}
