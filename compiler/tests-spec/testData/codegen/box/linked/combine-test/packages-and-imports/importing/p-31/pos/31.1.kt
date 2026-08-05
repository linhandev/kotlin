// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: same-file private top-level class is usable by other declarations in that file
 */

private class Secret56031(val v: Int = 8)

class Facade56031 {
    fun ok(): Int = Secret56031().v
}

// TESTCASE NUMBER: 1
fun test(): Int = Facade56031().ok()

fun box(): String {
    if (test() != 8) return "NOK"
    return "OK"
}
