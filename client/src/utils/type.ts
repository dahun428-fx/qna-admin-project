export type MayBe<T extends object = object> = { [K in keyof T]: unknown };
