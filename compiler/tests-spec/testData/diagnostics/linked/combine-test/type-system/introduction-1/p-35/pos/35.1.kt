// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 35 -> sentence 35
 * PRIMARY LINKS: declarations, classifier-declaration, value-class-declaration -> paragraph 35 -> sentence 35
 *                type-system, type-kinds, nullable-types -> paragraph 35 -> sentence 35
 *                type-system, introduction-1 -> paragraph 35 -> sentence 35
 *                expressions, when-expressions -> paragraph 35 -> sentence 35
 *                expressions, elvis-operator-expressions -> paragraph 35 -> sentence 35
 * NUMBER: 1
 * DESCRIPTION: nullable value class at T? boundary refined by when/Elvis type inference
 * HELPERS: checkType
 */

@JvmInline
value class UserId56235(val raw: Int)

fun <T> box56235(x: T): T? = x

// TESTCASE NUMBER: 1
fun case_1() {
    val u = box56235(UserId56235(2))
    checkSubtype<Int>(when (u) {
        null -> -1
        else -> u.raw
    })
    checkSubtype<Int>(u?.raw ?: -1)
}
