// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 277 -> sentence 277
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 277 -> sentence 277
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 277 -> sentence 277
 *                declarations, classifier-declaration, classifier-declaration-scopes -> paragraph 277 -> sentence 277
 * NUMBER: 1
 * DESCRIPTION: a private primary constructor is reachable from companion factories; covers plain class, generic class, and multi-arg factory; contrasts with previous-point outside-class failure, with p-85 singleton-cache focus, and with p-109/p-110 secondary-constructor focus
 */

// TESTCASE NUMBER: 1
class Vault private constructor(val v: Int) {
    companion object {
        fun open(): Vault = Vault(1)
        fun open(v: Int): Vault = Vault(v)
    }
}

// TESTCASE NUMBER: 2
class Box<T> private constructor(val value: T) {
    companion object {
        fun of(value: String): Box<String> = Box(value)
    }
}

// TESTCASE NUMBER: 3
class Token private constructor(val code: Int, val tag: String) {
    companion object {
        fun numbered(code: Int): Token = Token(code, "n")
        fun tagged(tag: String): Token = Token(0, tag)
    }
}

fun box(): String {
    if (Vault.open().v != 1) return "NOK: vault-open"
    if (Vault.open(7).v != 7) return "NOK: vault-open-7"
    val v = Vault.open(3)
    if (v.v != 3) return "NOK: via-vault"

    if (Box.of("ok").value != "ok") return "NOK: box"
    if (Box.of("ab").value != "ab") return "NOK: box-ab"
    val b: Box<String> = Box.of("ok")
    if (b.value != "ok") return "NOK: via-box"

    if (Token.numbered(9).code != 9) return "NOK: token-code"
    if (Token.numbered(9).tag != "n") return "NOK: token-tag-n"
    if (Token.tagged("ok").tag != "ok") return "NOK: token-tag"
    if (Token.tagged("ok").code != 0) return "NOK: token-zero"
    val t = Token.numbered(2)
    if (t.code != 2 || t.tag != "n") return "NOK: via-token"
    return "OK"
}
