/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, callables-and-invoke-convention -> paragraph 1 -> sentence 1
 * NUMBER: 13
 * DESCRIPTION: call X<T>() forwards type arguments to X.invoke<T>()
 */

class TypedHolder1145<T>

class CallableHolder1145 {
    operator fun <T> invoke(): TypedHolder1145<T> = TypedHolder1145()
}

val target1145 = CallableHolder1145()

// TESTCASE NUMBER: 1
fun box(): String {
    val strings: TypedHolder1145<String> = target1145<String>()
    val ints: TypedHolder1145<Int> = target1145<Int>()
    return if (strings is TypedHolder1145<*> && ints is TypedHolder1145<*>) "OK" else "NOK"
}
