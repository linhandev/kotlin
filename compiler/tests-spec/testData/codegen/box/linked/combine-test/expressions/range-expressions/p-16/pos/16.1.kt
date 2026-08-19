// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 16 -> sentence 16
 *                expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: extension operator rangeTo with in
 */

// TESTCASE NUMBER: 1
data class Tag(val id: Int)

operator fun Tag.rangeTo(o: Tag): IntRange = id..o.id

fun test(): Boolean = 2 in Tag(1)..Tag(3)

fun box(): String {
    if (!test()) return "NOK"
    if (0 in Tag(1)..Tag(3)) return "NOK"
    if (1 !in Tag(1)..Tag(3)) return "NOK"
    if (3 !in Tag(1)..Tag(3)) return "NOK"
    return "OK"
}
