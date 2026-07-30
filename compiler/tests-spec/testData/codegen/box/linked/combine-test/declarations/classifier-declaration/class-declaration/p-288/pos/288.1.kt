// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 288 -> sentence 288
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 288 -> sentence 288
 *                declarations, classifier-declaration, object-declaration -> paragraph 288 -> sentence 288
 * NUMBER: 1
 * DESCRIPTION: an internal object declaration is usable in the same module via public helpers that do not expose its type; covers plain object, object with members, and nested access; contrasts with p-262 internal class and with next-point enum private constructor
 */

// TESTCASE NUMBER: 1
internal object Token {
    val code: Int = 7
}

fun tokenCode(): Int = Token.code

// TESTCASE NUMBER: 2
internal object Label {
    fun text(): String = "L"
}

fun labelText(): String = Label.text()

// TESTCASE NUMBER: 3
internal object Flag {
    var on: Boolean = false
    fun flip(): Boolean {
        on = !on
        return on
    }
}

fun flagFlip(): Boolean = Flag.flip()

fun box(): String {
    if (tokenCode() != 7) return "NOK: token"
    if (Token.code != 7) return "NOK: token-direct"

    if (labelText() != "L") return "NOK: label"
    if (Label.text() != "L") return "NOK: label-direct"

    if (!flagFlip()) return "NOK: flag-on"
    if (flagFlip()) return "NOK: flag-off"
    if (Flag.on) return "NOK: flag-state"
    return "OK"
}
