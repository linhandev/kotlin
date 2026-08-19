// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 260 -> sentence 260
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 260 -> sentence 260
 *                inheritance, inheriting -> paragraph 260 -> sentence 260
 * NUMBER: 1
 * DESCRIPTION: a class without an explicit visibility modifier is public and can appear in public API signatures; contrasts with next-point explicit public and with private types that cannot be exposed publicly
 */

// TESTCASE NUMBER: 1
class Pub

fun makePub(): Pub = Pub()

// TESTCASE NUMBER: 2
class Token(val code: Int)

fun makeToken(): Token = Token(7)

class TokenClient {
    fun wrap(): Token = Token(7)
}

// TESTCASE NUMBER: 3
open class Base

class Derived : Base()

fun asBase(): Base = Derived()

fun box(): String {
    if (makePub()::class != Pub::class) return "NOK: pub-class"
    val p: Pub = makePub()
    if (p::class.simpleName != "Pub") return "NOK: pub-name"

    if (makeToken().code != 7) return "NOK: token-code"
    if (TokenClient().wrap().code != 7) return "NOK: token-client"
    val t: Token = makeToken()
    if (t.code != 7) return "NOK: via-token"

    val b: Base = asBase()
    if (b !is Derived) return "NOK: derived-is"
    if (Derived()::class.simpleName != "Derived") return "NOK: derived-name"
    return "OK"
}
