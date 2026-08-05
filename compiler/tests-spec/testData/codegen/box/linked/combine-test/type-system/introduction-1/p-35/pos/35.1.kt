// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 35 -> sentence 35
 * PRIMARY LINKS: declarations, classifier-declaration, value-class-declaration -> paragraph 35 -> sentence 35
 *                type-system, type-kinds, nullable-types -> paragraph 35 -> sentence 35
 *                type-system, introduction-1 -> paragraph 35 -> sentence 35
 *                expressions, when-expressions -> paragraph 35 -> sentence 35
 *                expressions, elvis-operator-expressions -> paragraph 35 -> sentence 35
 * NUMBER: 1
 * DESCRIPTION: nullable value class at T? boundary refined by when/Elvis
 */

// TESTCASE NUMBER: 1
@JvmInline
value class UserId56235(val raw: Int)

fun <T> box56235(x: T): T? = x

fun read56235(u: UserId56235?): Int {
    val viaWhen = when (u) {
        null -> -1
        else -> u.raw
    }
    val viaElvis = u?.raw ?: -1
    return viaWhen + viaElvis
}

fun box(): String {
    if (read56235(box56235(UserId56235(2))) != 4) return "NOK"
    if (read56235(null) != -2) return "NOK"
    return "OK"
}
