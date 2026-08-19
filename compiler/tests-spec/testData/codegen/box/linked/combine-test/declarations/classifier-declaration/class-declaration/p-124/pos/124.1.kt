// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 124 -> sentence 124
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 124 -> sentence 124
 *                declarations, property-declaration, late-initialized-properties -> paragraph 124 -> sentence 124
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 124 -> sentence 124
 * NUMBER: 1
 * DESCRIPTION: lateinit var property may be assigned in init block before first use in class declaration
 */

// TESTCASE NUMBER: 1
class Host(val seed: String) {
    lateinit var name: String

    init {
        name = seed
    }

    fun get(): String = name
}

fun viaOk(): String = Host("ok").get()

fun viaHi(): String = Host("hi").get()

fun viaAlpha(): String = Host("A").get()

fun box(): String {
    if (viaOk() != "ok") return "NOK: ok"
    if (viaHi() != "hi") return "NOK: hi"
    if (viaAlpha() != "A") return "NOK: alpha"
    return "OK"
}
