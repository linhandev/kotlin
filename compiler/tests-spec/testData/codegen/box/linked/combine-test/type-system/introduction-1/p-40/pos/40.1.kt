// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 40 -> sentence 40
 * PRIMARY LINKS: declarations, classifier-declaration, value-class-declaration -> paragraph 40 -> sentence 40
 *                type-system, introduction-1 -> paragraph 40 -> sentence 40
 *                expressions, cast-expressions -> paragraph 40 -> sentence 40
 * NUMBER: 1
 * DESCRIPTION: reading wrong element type from an erased generic container fails at use-site
 */

// TESTCASE NUMBER: 1
@JvmInline
value class UserId56240(val raw: Int)

@Suppress("UNCHECKED_CAST")
fun test56240(): Int {
    val xs: Any = listOf(UserId56240(1))
    val ys = xs as List<String>
    return ys.first().length
}

fun box(): String {
    return try {
        test56240()
        "NOK"
    } catch (e: ClassCastException) {
        "OK"
    }
}
