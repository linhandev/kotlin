// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 138 -> sentence 138
 * PRIMARY LINKS: declarations, property-declaration -> paragraph 138 -> sentence 138
 *                declarations, property-declaration, getters-and-setters -> paragraph 138 -> sentence 138
 * NUMBER: 1
 * DESCRIPTION: instance val with custom getter and no backing field needs no initializer in class declaration
 */

// TESTCASE NUMBER: 1
class RO(val x: Int) {
    val doubled: Int
        get() = x * 2
}

// TESTCASE NUMBER: 2
class Named(val label: String) {
    val upper: String
        get() = label.uppercase()
}

// TESTCASE NUMBER: 3
class Tracked(val seed: Int) {
    val log = mutableListOf<String>()
    val scaled: Int
        get() {
            log += "get"
            return seed * 3
        }

    init {
        log += "init"
    }
}

fun viaInt(x: Int): Int = RO(x).doubled

fun viaString(label: String): String = Named(label).upper

fun constructionDoesNotInvokeGetter(): List<String> = Tracked(4).log

fun afterAccess(): Pair<Int, List<String>> {
    val t = Tracked(4)
    val value = t.scaled
    return value to t.log
}

fun box(): String {
    if (viaInt(3) != 6) return "NOK: int-3"
    if (viaInt(0) != 0) return "NOK: int-0"
    if (viaInt(-2) != -4) return "NOK: int-neg"
    if (viaString("ab") != "AB") return "NOK: string"
    if (viaString("") != "") return "NOK: empty"
    if (constructionDoesNotInvokeGetter() != listOf("init")) return "NOK: init-only"
    val (value, log) = afterAccess()
    if (value != 12) return "NOK: scaled"
    if (log != listOf("init", "get")) return "NOK: access-order"
    return "OK"
}
