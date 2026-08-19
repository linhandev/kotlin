// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: type-system, type-kinds, flexible-types, platform-types -> paragraph 20 -> sentence 20
 *                expressions, not-null-assertion-expressions -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: non-null assertion on a null platform type fails with NPE
 */

// TESTCASE NUMBER: 1
fun box(): String {
    return try {
        System.getProperty("no.such.prop.abc.56220")!!
        "NOK"
    } catch (e: NullPointerException) {
        "OK"
    }
}
