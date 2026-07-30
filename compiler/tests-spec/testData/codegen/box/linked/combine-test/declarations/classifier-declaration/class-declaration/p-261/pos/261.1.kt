// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 261 -> sentence 261
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 261 -> sentence 261
 *                inheritance, inheriting -> paragraph 261 -> sentence 261
 * NUMBER: 1
 * DESCRIPTION: an explicitly public class can appear in public API signatures; contrasts with p-260 default public (no modifier) and with next-point internal which cannot be exposed from public functions
 */

// TESTCASE NUMBER: 1
public class Pub

fun makePub(): Pub = Pub()

// TESTCASE NUMBER: 2
public class Token(val code: Int)

fun makeToken(): Token = Token(7)

public class TokenClient {
    fun wrap(): Token = Token(7)
}

// TESTCASE NUMBER: 3
public open class Base

public class Derived : Base()

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
