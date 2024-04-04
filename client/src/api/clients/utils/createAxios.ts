import { isObject } from "@/utils/object";
import axios, { AxiosError, AxiosRequestConfig } from "axios";

type AxiosErrorHandler = (error: AxiosError) => void;

type createAxiosConfig = AxiosRequestConfig & {
  handleError?: AxiosErrorHandler;
};

export function createAxios(config: createAxiosConfig = {}, options?: { shouldSendApiLog?: true }) {
  const $axios = axios.create({});
}

function paramSerializer(params: unknown) {
  // if(isObject)
}
